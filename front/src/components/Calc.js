import React, {useEffect} from 'react';
import {Form, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";

const emitLoadingError = (message) => {
    emitCustomEvent('error-event', message);
}

async function getAllCalculations(id) {

}


async function getBmi(userId) {
    emitCustomEvent('start-loading');
    return fetchWithTimeout('http://localhost:8080/calc/body_mass_index?id=' + userId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(data => data.json())
        .finally(_ => {
            emitCustomEvent('loaded');
        })
}

async function getCaloriesMSJ(id) {
    emitCustomEvent('start-loading');
    return fetchWithTimeout('http://localhost:8080/calc/calories_mifflin_stjeor?id=', +id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(data => data.json())
        .finally(_ => {
            emitCustomEvent('loaded');
        })
}

async function getIdealWeightLorenz(id) {
    emitCustomEvent('start-loading');
    return fetchWithTimeout('http://localhost:8080/calc/ideal_weight_lorenz?id=', +id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(data => data.json())
        .finally(_ => {
            emitCustomEvent('loaded');
        })
}

async function getIdealWeightDevine(id) {
    emitCustomEvent('start-loading');
    return fetchWithTimeout('http://localhost:8080/calc/ideal_weight_devine?id=', +id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(data => data.json())
        .finally(_ => {
            emitCustomEvent('loaded');
        })
}

async function getIdealWeightBroca(id) {
    emitCustomEvent('start-loading');
    return fetchWithTimeout('http://localhost:8080/calc/ideal_weight_broca?id=', +id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(data => data.json())
        .finally(_ => {
            emitCustomEvent('loaded');
        })
}

export default function Calc() {

    const [form] = Form.useForm()

    useEffect(() => {

        getAllCalculations(1).then(result => {
            form.setFieldValue('bodyMassIndex',result.bmi)
            form.setFieldValue('idealWeightBroca', result.idealWeightBroca)
            form.setFieldValue('idealWeightLorenz', result.idealWeightLorenz)
            form.setFieldValue('idealWeightDevine', result.idealWeightDevine)
            form.setFieldValue('сaloriesMSJ', result.caloriesMSJ)

        })
    }, []);


    const onFinish = (values) => {
        getAllCalculations(1)
    }

    return (<>
        <h1>Расчеты</h1>
        <Form
            form={form}
            name="calc"
            onFinish={onFinish}>
            <Form.Item
                name="bodyMassIndex"
                label="Индекс массы тела(1835)"
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="idealWeightBroca"
                label="Вес по Брока(1871)"
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="idealWeightLorenz"
                label="Вес по Лоренцу(1929)"
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="idealWeightDevine"
                label="Вес по Девайну(1974)"
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="сaloriesMSJ"
                label="Калории по Миффлин—Сан-Жеора(1990)"
            >
                <Input/>
            </Form.Item>

        </Form>
    </>)
}