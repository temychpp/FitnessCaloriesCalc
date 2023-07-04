import React, {useEffect} from 'react';
import {Form, Input} from 'antd'

async function getBmi(userId) {
    // todo move rest configs to separate file
    return fetch('http://localhost:8080/calc/bmi?id=' + userId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(data => data.json())
        .catch(_ => null)
    // todo catch and show exceptions
}

async function getCalories(id) {
    // todo move rest configs to separate file
    return fetch('http://localhost:8080/calc/calories?id=', +id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(data => data.json())
        .catch(_ => null)
    // todo catch and show exceptions
}

export default function Calc() {

    const [form] = Form.useForm()

    useEffect(() => {
        // if (fields.is_loaded === false) {
        getBmi(1).then(result => {
            form.setFieldValue('bodyMassIndex', result.bodyMassIndex)
        })
        getCalories(1).then(result => {
            form.setFieldValue('cal', result.cal)
        })

        // }
    }, []);

    const onFinish = (values) => {
        getCalories(values, 1)
    }
    return (<>
        <h1>Расчеты</h1>
        <Form
            form={form}
            name="calc"
            onFinish={onFinish}        >
            <Form.Item
                name="bodyMassIndex"
                label="Индекс массы тела"
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="cal"
                label="Базовый энергообмен"
            >
                <Input/>
            </Form.Item>




        </Form>
    </>)
}