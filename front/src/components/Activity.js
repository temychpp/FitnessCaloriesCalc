import React, {useEffect} from 'react';
import {Button, Form, Input} from 'antd'

async function getActivity(userId) {
    // todo move rest configs to separate file
    return fetch('http://localhost:8080/person/activity?id=' + userId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(data => data.json())
        .catch(_ => null)
    // todo catch and show exceptions
}

async function postActivity(formData, id) {
    // todo move rest configs to separate file
    return fetch('http://localhost:8080/person/activity?id=', +id, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            stepsByDay: formData.stepsByDay,
            fitnessByDay: formData.fitnessByDay,
            aerobicsByDay: formData.aerobicsByDay,
        })
    })
        .then(data => data.json())
        .catch(_ => null)
    // todo catch and show exceptions
}

export default function Activity() {

    const [form] = Form.useForm()

    useEffect(() => {
        // if (fields.is_loaded === false) {
        getActivity(1).then(result => {
            form.setFieldValue('stepsByDay', result.stepsByDay)
            form.setFieldValue('fitnessByDay', result.fitnessByDay)
            form.setFieldValue('aerobicsByDay', result.aerobicsByDay)
        })
        // }
    }, []);

    const onFinish = (values) => {
        postActivity(values, 1)

    }
    return (<>
        <h1>Активность</h1>
        <Form
            form={form}
            name="activity"
            onFinish={onFinish}        >

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
            <Button type="primary" htmlType="submit">
                Сохранить
            </Button>
        </Form>
    </>)
}