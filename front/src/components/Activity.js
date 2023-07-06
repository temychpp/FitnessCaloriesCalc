import React, {useEffect} from 'react';
import {Button, Form, Select, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";

const emitLoadingError = (message) => {
    emitCustomEvent('error-event', message);
}

async function getActivity(userId) {
    // todo move rest configs to separate file
    emitCustomEvent('start-loading');
    return fetchWithTimeout('http://localhost:8080/person/activity?id=' + userId,
        {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(data => data.json())
        .finally(_ => {
            emitCustomEvent('loaded');
        })
}

async function postActivity(formData, id) {
    // todo move rest configs to separate file
    emitCustomEvent('start-loading');
    return fetchWithTimeout('http://localhost:8080/person/activity', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            stepsByDay: formData.stepsByDay,
            fitnessByDay: formData.fitnessByDay,
            aerobicsByDay: formData.aerobicsByDay,
            activityCoefficient: formData.activityCoefficient,
        })
    })
        .then(data => data.json())
        .catch(_ => null)
        .finally(data => {
            emitCustomEvent('loaded');
        })
    // todo catch and show exceptions
}

export default function Activity() {

    const [form] = Form.useForm()

    useEffect(() => {
        getActivity(1).then(result => {
            form.setFieldValue('stepsByDay', result.stepsByDay)
            form.setFieldValue('fitnessByDay', result.fitnessByDay)
            form.setFieldValue('aerobicsByDay', result.aerobicsByDay)
            form.setFieldValue('activityCoefficient', result.activityCoefficient)
        }).catch(_ => emitLoadingError('Ошибка загрузки активности'))
    }, []);

    const onFinish = (values) => {
        postActivity(values, 1)
            .catch(_ => emitLoadingError('Ошибка сохранения активности'))
    }

    return (<>
        <h1>Активность</h1>
        <Form
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
        </Form>
    </>)
}