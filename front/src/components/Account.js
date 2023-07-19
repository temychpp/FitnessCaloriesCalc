// import React from 'react';
//
// export default function Account() {
//     return (<h1>account settings</h1>)
// }


import React, {useEffect} from 'react';
import {Button, Form, Select, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";
import {emitLoadingError, LOADED, START_LOADING} from "../core/loadEvents";
import {updatePersonUrl, getUrl, patch_rq} from "../core/urlResolver";
import {getAccount} from "../core/account";



async function patchUpdate(formData) {
    let body = JSON.stringify({
        id:getAccount().id,
        name: formData.name,
        email: formData.email,
        oldPassword: formData.oldPassword,
        password: formData.password
    });
    emitCustomEvent(START_LOADING);
    return fetchWithTimeout(getUrl(updatePersonUrl), patch_rq(body))
        .then(data => data.json())
        .finally(_ => {
            emitCustomEvent(LOADED);
        })
}

export default function Update() {
    const [form] = Form.useForm()

    const onFinish = (values) => {
        patchUpdate(values)
            .catch(_ => emitLoadingError('Ошибка изменения данных'))
    }

    return (<>
        <h1>Персональные данные</h1>
        {
            <Form
                form={form}
                name="update"
                onFinish={onFinish}
            >

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
                    name="oldPassword"
                    label="Старый пароль"
                >
                    <Input.Password/>
                </Form.Item>

                <Form.Item
                    name="password"
                    label="Новый пароль"
                >
                    <Input.Password/>
                </Form.Item>
                <Button type="primary" htmlType="submit">
                    Отправить
                </Button>
            </Form>}
    </>)
}