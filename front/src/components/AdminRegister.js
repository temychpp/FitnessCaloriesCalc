import React, {useEffect} from 'react';
import {Button, Form, Select, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";
import {emitLoadingError, LOADED, START_LOADING} from "../core/loadEvents";
import {adminRegisterUrl, getUrl, post_rq} from "../core/urlResolver";


async function postRegister(formData) {
    let body = JSON.stringify({
        name: formData.name,
        role: formData.role,
        email: formData.email,
        password: formData.password,
        confirmPassword: formData.confirmPassword
    });
    emitCustomEvent(START_LOADING);
    return fetchWithTimeout(getUrl(adminRegisterUrl), post_rq(body))
        .then(data => data.json())
        .finally(_ => {
            emitCustomEvent(LOADED);
        })
}

export default function Register() {
    const [form] = Form.useForm()

    const onFinish = (values) => {
        postRegister(values)
            .catch(_ => emitLoadingError('Ошибка регистрации'))
    }

    return (<>
        <h1>Регистрация</h1>
        {
            <Form
                form={form}
                name="register"
                onFinish={onFinish}
            >
                <Form.Item
                    name="role"
                    label="Роль"
                >
                    <Select placeholder="выберите роль">
                        <Select.Option value="USER">Пользователь</Select.Option>
                        <Select.Option value="ADMIN">Админ</Select.Option>
                    </Select>
                </Form.Item>
                <Form.Item
                    name="name"
                    label="Имя"
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="email"
                    label="email"
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="password"
                    label="Пароль"
                >
                    <Input.Password/>
                </Form.Item>

                <Form.Item
                    name="confirmPassword"
                    label="Подтверждение пароля"
                >
                    <Input.Password/>
                </Form.Item>

                <Button type="primary" htmlType="submit">
                    Зарегистрироваться
                </Button>
            </Form>}
    </>)
}