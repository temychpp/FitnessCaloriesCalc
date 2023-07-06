import React, {useEffect} from 'react';
import {Button, Form, Select, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";

const emitLoadingError = (message) => {
    emitCustomEvent('error-event', message);
}

async function getAnthro(userId) {
    // todo move rest configs to separate file
    emitCustomEvent('start-loading');
    return fetchWithTimeout('http://localhost:8080/pe111rson/anthro?id=' + userId,
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


async function postAnthro(formData, id) {
    // todo move rest configs to separate file
    emitCustomEvent('start-loading');
    return fetchWithTimeout('http://localhost:8080/person/anthro', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            age: formData.age,
            height: formData.height,
            weight: formData.weight,
            gender: formData.gender
        })
    })
        .then(data => data.json())
        .finally(_ => {
            emitCustomEvent('loaded');
        })
}

export default function Anthro() {

    const [form] = Form.useForm()

    useEffect(() => {

        getAnthro(1).then(result => {
            form.setFieldValue('age', result.age)
            form.setFieldValue('gender', result.gender)
            form.setFieldValue('height', result.height)
            form.setFieldValue('weight', result.weight)
        }).catch(_ => emitLoadingError('Ошибка загрузки антропометрии'))
    }, []);

    const onFinish = (values) => {
        postAnthro(values, 1)
            .catch(_ => emitLoadingError('Ошибка сохранения антропометрии'))
    }

    return (<>
        <h1>Антропометрия</h1>
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
                    <Select.Option value="male">мужской</Select.Option>
                    <Select.Option value="female">женский</Select.Option>
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
        </Form>
    </>)
}