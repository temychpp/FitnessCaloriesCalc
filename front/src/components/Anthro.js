import React, {useEffect} from 'react';
import {Button, Form, Select, Input} from 'antd'

async function getAnthro(userId) {
    // todo move rest configs to separate file
    return fetch('http://localhost:8080/person/anthro?id=' + userId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(data => data.json())
        .catch(_ => null)
    // todo catch and show exceptions
}


async function postAnthro(formData, id) {
    // todo move rest configs to separate file
    return fetch('http://localhost:8080/person/anthro', {
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
    }, []);

    const onFinish = (values) => {
        postAnthro(values, 1)
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