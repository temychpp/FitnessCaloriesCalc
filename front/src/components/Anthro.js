import React, {useEffect} from 'react';
import {Form, Select, Input} from 'antd'

async function getAnthro(userId) {
    // todo move rest configs to separate file
    return fetch('http://localhost:8080/anthro?id=' + userId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(data => data.json())
        .catch(_ => null)
    // todo catch and show exceptions
}

export default function Anthro() {

    const [ form ] = Form.useForm()

    useEffect(() => {
        // if (fields.is_loaded === false) {
            getAnthro(1).then(result => {
                form.setFieldValue('age', result.age)
                form.setFieldValue('gender', result.gender)
                form.setFieldValue('height', result.height)
                form.setFieldValue('weight', result.weight)
            })
        // }
    }, [])

    return (<>
        <h1>Антропометрия</h1>
        <Form
            form={form}
            name="anthro"
        >
            <Form.Item
                name="gender"
                label="Пол"
                rules={[
                    {
                        required: true,
                        message: 'Please select gender!',
                    },
                ]}
            >
                <Select placeholder="выберите ваш пол">
                    <Select.Option value="male">мужской</Select.Option>
                    <Select.Option value="female">женский</Select.Option>
                </Select>
            </Form.Item>
            <Form.Item
                name="age"
                label="Возраст"
                rules={[
                    {
                        type: 'number',
                        message: 'неверный возраст',
                    },
                    {
                        required: true,
                        message: 'Пожалуйста введите возраст',
                    },
                ]}
            >
                <Input/>
            </Form.Item>
            <Form.Item
                name="height"
                label="Рост"
                rules={[
                    {
                        type: 'number',
                        message: 'неверный рост',
                    },
                    {
                        required: true,
                        message: 'Пожалуйста введите рост',
                    },
                ]}
            >
                <Input/>
            </Form.Item>
            <Form.Item
                name="weight"
                label="Вес"
                rules={[
                    {
                        type: 'number',
                        message: 'неверный вес',
                    },
                    {
                        required: true,
                        message: 'Пожалуйста введите вес',
                    },
                ]}
            >
                <Input/>
            </Form.Item>
        </Form>
    </>)
}