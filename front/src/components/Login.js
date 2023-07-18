import React, {useState} from 'react';
import {Modal, Alert, Form, Input, Menu} from 'antd';
import {emitCustomEvent} from 'react-custom-events'
import {updateAccount} from "../core/account";
import {ACCOUNT_UPDATE, emitLoadingError} from "../core/loadEvents";
import {Link} from "react-router-dom";
import fetchWithTimeout from "../core/fetchWithTimeout";
import {getUrl, loginUrl} from "../core/urlResolver";

async function loginUser(credentials) {
    return fetchWithTimeout(getUrl(loginUrl), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())
        .catch(_ => emitLoadingError('Ошибка логина'))
}

export default function Login() {
    const [username, setUserName] = useState();
    const [password, setPassword] = useState();
    const [notice, setNotice] = useState();
    const [isOpen, setIsOpen] = useState();
    const [confirmLoading, setConfirmLoading] = useState();

    const handleSubmit = async _ => {
        setConfirmLoading(true);
        await loginUser({
            username,
            password // todo hash password
        }).then(rs => {
            if (rs === null) {
                setNotice('Incorrect login/password');
            } else {
                updateAccount({
                    'token': rs.token,
                    'id': rs.id,
                    'name': username
                });
                setIsOpen(false)
                setConfirmLoading(false);
                emitCustomEvent(ACCOUNT_UPDATE);
            }
        }).catch(_ => {
            emitLoadingError("Ошибка логина")
            setConfirmLoading(false);
        });
    }

    const showModal = () => {
        setIsOpen(true);
    }

    const hideModal = () => {
        setIsOpen(false);
    }

    const items = [
        {
            label: (<Link to="/register">Зарегистрироваться</Link>),
            key: 'register'
        },
        {
            label: (<Link to='#'>Войти</Link>),
            key: 'login'
        },
    ];

    const onClick = (key) => {
        if (key.key === "login") {
            showModal();
        }
    }

    return (
        <>
            <Menu
                mode="horizontal"
                items={items}
                onClick={onClick}
            />
            <Modal
                open={isOpen}
                onOk={handleSubmit}
                onCancel={hideModal}
                confirmLoading={confirmLoading}
                title='Log in'
            >
                <Form
                    name="basic"
                >
                    {notice && (
                        <Alert
                            message={notice}
                            type="error"
                            showIcon
                        />
                    )}
                    <Form.Item
                        label="Username"
                        name="username"
                        rules={[{required: true, message: 'Please input your username!'}]}
                        onChange={e => setUserName(e.target.value)}
                    >
                        <Input/>
                    </Form.Item>

                    <Form.Item
                        label="Password"
                        name="password"
                        rules={[{required: true, message: 'Please input your password!'}]}
                        onChange={e => setPassword(e.target.value)}
                    >
                        <Input.Password/>
                    </Form.Item>
                </Form>
            </Modal>
        </>
    )
};
