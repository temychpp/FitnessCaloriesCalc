import React, {useEffect} from 'react';
import {Button, Form, Select, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";
import {getAccount} from "../core/account";
import {emitLoadingError, LOADED, START_LOADING} from "../core/loadEvents";
import {dayMealUrl, get_rq, getUrl, post_rq} from "../core/urlResolver";

async function postMeal(formData) {
    let body = JSON.stringify({
        product:formData.product,
        meal: formData.meal,
        quantity:formData.meal,
    });
    emitCustomEvent(START_LOADING);
    return fetchWithTimeout(getUrl(dayMealUrl), post_rq(body))
        .then(data => data.json())
        .finally(_ => {
            emitCustomEvent(LOADED);
        })
}

export default function Meal() {
    const account = getAccount();
    const [form] = Form.useForm()


    const onFinish = (values) => {
        postMeal(values, account.id)
            .catch(_ => emitLoadingError('Ошибка сохранения еды'))
    }



    return (<>
        <h4>Прием пищи за день</h4>
        {account !== null &&
            <Form
                form={form}
                name="dayMeal"
                onFinish={onFinish}
            >
                <Form.Item
                    name="meal"
                    label="Прием пищи"
                >
                    <Select placeholder="выберите прием пищи">
                        <Select.Option value="BREAKFAST">BREAKFAST</Select.Option>
                        <Select.Option value="LUNCH">LUNCH</Select.Option>
                        <Select.Option value="DINNER">DINNER</Select.Option>
                        <Select.Option value="SUPPER">SUPPER</Select.Option>
                        <Select.Option value="OTHER">OTHER</Select.Option>
                    </Select>
                </Form.Item>

                <Form.Item
                    name="productName"
                    label="Название продукта"
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="productQt"
                    label="Количество продукта(гр)"
                >
                    <Input/>
                </Form.Item>

                <Button type="primary" htmlType="submit">
                    Сохранить
                </Button>
            </Form>}
    </>)
}