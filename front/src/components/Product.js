import React, {useEffect} from 'react';
import {Button, Form, Select, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";
import {getAccount} from "../core/account";
import {emitLoadingError, LOADED, START_LOADING} from "../core/loadEvents";
import {createProductUrl, get_rq, getUrl, post_rq} from "../core/urlResolver";

async function getProduct() {
    emitCustomEvent(START_LOADING);
    return fetchWithTimeout(getUrl(createProductUrl), get_rq())
        .then(data => data.json())
        .finally(_ => {
            emitCustomEvent(LOADED);
        })

}

async function postProduct(formData) {
    let body = JSON.stringify({
        name: formData.name,
        calories: formData.calories,
        protein: formData.protein,
        fat:formData.fat,
        carbohydrates: formData.carbohydrates
    });

    emitCustomEvent(START_LOADING);
    return fetchWithTimeout(getUrl(createProductUrl), post_rq(body))
        .then(data => data.json())
        .finally(_ => {
            emitCustomEvent(LOADED);
        })
}

export default function Product() {
    const account = getAccount();
    const [form] = Form.useForm()

    useEffect(() => {
        if (account !== null) {
            getProduct(account.id).then(result => {
                form.setFieldValue('name', result.name)
                form.setFieldValue('calories', result.calories)
                form.setFieldValue('protein', result.protein)
                form.setFieldValue('fat', result.fat)
                form.setFieldValue('carbohydrates', result.carbohydrates)
            }).catch(_ => emitLoadingError('Ошибка загрузки продукта'))
        }
    }, []);

    const onFinish = (values) => {
        postProduct(values, account.id)
            .catch(_ => emitLoadingError('Ошибка сохранения продукта'))
    }

    return (<>
        <h1>Продукт</h1>
        {account !== null &&
            <Form
                form={form}
                name="product"
                onFinish={onFinish}
            >

                <Form.Item
                    name="name"
                    label="Название"
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="calories"
                    label="Калории"
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="protein"
                    label="Белки"
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="fat"
                    label="Жиры"
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="carbohydrates"
                    label="Углеводы"
                >
                    <Input/>
                </Form.Item>

                <Button type="primary" htmlType="submit">
                    Сохранить
                </Button>
            </Form>}
    </>)
}