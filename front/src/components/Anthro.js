import React, {useEffect} from 'react';
import {Button, Form, Select, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";
import {getAccount} from "../core/account";
import {emitLoadingError, LOADED, START_LOADING} from "../core/loadEvents";
import {anthroUrl, get_rq, getUrl, post_rq} from "../core/urlResolver";

async function getAnthro(userId) {
    emitCustomEvent(START_LOADING);
    return fetchWithTimeout(getUrl(anthroUrl, '?id=' + userId), get_rq())
        .then(data => data.json())
        .finally(_ => {
            emitCustomEvent(LOADED);
        })
}

async function postAnthro(formData, userId) {
    let body = JSON.stringify({
        id: userId,
        age: formData.age,
        height: formData.height,
        weight: formData.weight,
        gender: formData.gender
    });
    emitCustomEvent(START_LOADING);
    return fetchWithTimeout(getUrl(anthroUrl), post_rq(body))
        .then(data => data.json())
        .finally(_ => {
            emitCustomEvent(LOADED);
        })
}

export default function Anthro() {
    const account = getAccount();
    const [form] = Form.useForm()

    useEffect(() => {
        if (account !== null) {
            getAnthro(account.id).then(result => {
                form.setFieldValue('age', result.age)
                form.setFieldValue('gender', result.gender)
                form.setFieldValue('height', result.height)
                form.setFieldValue('weight', result.weight)
            }).catch(_ => emitLoadingError('Ошибка загрузки антропометрии'))
        }
    }, []);

    const onFinish = (values) => {
        postAnthro(values, account.id)
            .catch(_ => emitLoadingError('Ошибка сохранения антропометрии'))
    }

    return (<>
        <h1>Антропометрия</h1>
        {account !== null &&
            <Form
                form={form}
                name="anthro"
                onFinish={onFinish}
            >
                <Form.Item
                    name="gender"
                    label="Пол"
                >
                    <Select placeholder="выберите ваш пол">
                        <Select.Option value="MALE">мужской</Select.Option>
                        <Select.Option value="FEMALE">женский</Select.Option>
                    </Select>
                </Form.Item>
                <Form.Item
                    name="age"
                    label="Возраст"
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="height"
                    label="Рост"
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="weight"
                    label="Вес"
                >
                    <Input/>
                </Form.Item>
                <Button type="primary" htmlType="submit">
                    Сохранить
                </Button>
            </Form>}
    </>)
}