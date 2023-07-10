import React, {useEffect} from 'react';
import {Button, Form, Select, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";
import {getAccount} from "../core/account";
import {activityUrl, GET_REQUEST, getUrl, post_rq} from "../core/urlResolver";
import {ERROR_EVENT, LOADED, START_LOADING} from "../core/loadEvents";

const emitLoadingError = (message) => {
    emitCustomEvent(ERROR_EVENT, message);
}

async function getActivity(userId) {
    emitCustomEvent(START_LOADING);
    return fetchWithTimeout(getUrl(activityUrl, '?id=' + userId), GET_REQUEST)
        .then(data => data.json())
        .finally(_ => {
            emitCustomEvent(LOADED);
        })
}

async function postActivity(formData, userId) {
    emitCustomEvent(START_LOADING);
    let body = JSON.stringify({
        id: userId,
        stepsByDay: formData.stepsByDay,
        fitnessByDay: formData.fitnessByDay,
        aerobicsByDay: formData.aerobicsByDay,
        activityCoefficient: formData.activityCoefficient,
    });
    return fetchWithTimeout(getUrl(activityUrl), post_rq(body))
        .then(data => data.json())
        .catch(_ => null)
        .finally(_ => {
            emitCustomEvent(LOADED);
        })
}

export default function Activity() {
    const account = getAccount();
    const [form] = Form.useForm()

    useEffect(() => {
        if (account !== null) {
            getActivity(account.id).then(result => {
                form.setFieldValue('stepsByDay', result.stepsByDay)
                form.setFieldValue('fitnessByDay', result.fitnessByDay)
                form.setFieldValue('aerobicsByDay', result.aerobicsByDay)
                form.setFieldValue('activityCoefficient', result.activityCoefficient)
            }).catch(_ => emitLoadingError('Ошибка загрузки активности'))
        }
    }, []);

    const onFinish = (values) => {
        postActivity(values, account.id)
            .catch(_ => emitLoadingError('Ошибка сохранения активности'))
    }

    return (<>
        <h1>Активность</h1>
        {account !== null && <Form
            form={form}
            name="activity"
            onFinish={onFinish}>

            <Form.Item
                name="stepsByDay"
                label="Шаги"
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="fitnessByDay"
                label="Фитнес"
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="aerobicsByDay"
                label="Аэробика"
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="activityCoefficient"
                label="Активности"
            >
                <Select placeholder="выберите уровень вашей активности">
                    <Select.Option value="ONE">минимальная активность</Select.Option>
                    <Select.Option value="TWO">слабый уровень активности</Select.Option>
                    <Select.Option value="THREE">+слабый уровень активности+</Select.Option>
                    <Select.Option value="FOUR">умеренный уровень активности</Select.Option>
                    <Select.Option value="FIVE">+умеренный уровень активности+</Select.Option>
                    <Select.Option value="SIX">тяжелая активность</Select.Option>
                    <Select.Option value="SEVEN">экстремальный уровень</Select.Option>
                </Select>
            </Form.Item>

            <Button type="primary" htmlType="submit">
                Сохранить
            </Button>
        </Form>}
    </>)
}