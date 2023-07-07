import React, {useEffect} from 'react';
import {Form, Input} from 'antd'
import {emitCustomEvent} from "react-custom-events";
import fetchWithTimeout from "../core/fetchWithTimeout";
import {getAccount} from "../core/account";
import {calcUrl, GET_REQUEST, getUrl} from "../core/urlResolver";
import {ERROR_EVENT, LOADED, START_LOADING} from "../core/loadEvents";

const emitLoadingError = (message) => {
    emitCustomEvent(ERROR_EVENT, message);
}

let results = {
    bmi: '',
    msj: '',
    lorenz: '',
    devine: '',
    broca: ''
}

async function getAllCalculations(id) {
    await Promise.all([
        getBmi(id).then(data => results.bmi = data),
        getCaloriesMSJ(id).then(data => results.msj = data),
        getIdealWeightLorenz(id).then(data => results.lorenz = data),
        getIdealWeightDevine(id).then(data => results.devine = data),
        getIdealWeightBroca(id).then(data => results.broca = data)
    ])
}


async function getBmi(userId) {
    return fetchWithTimeout(getUrl(calcUrl, '/body_mass_index?id=' + userId), GET_REQUEST)
        .then(data => data.json())
}

async function getCaloriesMSJ(id) {
    return fetchWithTimeout(getUrl(calcUrl, '/calories_mifflin_stjeor?id=' + id), GET_REQUEST)
        .then(data => data.json())
}

async function getIdealWeightLorenz(id) {
    return fetchWithTimeout(getUrl(calcUrl,'/ideal_weight_lorenz?id=' + id), GET_REQUEST)
        .then(data => data.json())
}

async function getIdealWeightDevine(id) {
    return fetchWithTimeout(getUrl(calcUrl, '/ideal_weight_devine?id=' + id), GET_REQUEST)
        .then(data => data.json())
}

async function getIdealWeightBroca(id) {
    return fetchWithTimeout(getUrl(calcUrl, '/ideal_weight_broca?id=' + id), GET_REQUEST)
        .then(data => data.json())
}

export default function Calc() {
    const account = getAccount();

    const [form] = Form.useForm()

    useEffect(() => {
        if (account !== null) {
        emitCustomEvent(START_LOADING);
        getAllCalculations(account.id).then(_ => {
            form.setFieldValue('bodyMassIndex', results.bmi)
            form.setFieldValue('idealWeightBroca', results.msj)
            form.setFieldValue('idealWeightLorenz', results.lorenz)
            form.setFieldValue('idealWeightDevine', results.devine)
            form.setFieldValue('сaloriesMSJ', results.broca)
        }).catch(_ => emitLoadingError('Ошибка загрузки расчетов'))
            .finally(_ => {
                emitCustomEvent(LOADED);
            })
    }}, []);


    return (<>
        <h1>Расчеты</h1>
        {account !== null && <Form
            form={form}
            name="calc">
            <Form.Item
                name="bodyMassIndex"
                label="Индекс массы тела(1835)"
            >
                <Input disabled={true}/>
            </Form.Item>

            <Form.Item
                name="idealWeightBroca"
                label="Вес по Брока(1871)"
            >
                <Input disabled={true}/>
            </Form.Item>

            <Form.Item
                name="idealWeightLorenz"
                label="Вес по Лоренцу(1929)"
            >
                <Input disabled={true}/>
            </Form.Item>

            <Form.Item
                name="idealWeightDevine"
                label="Вес по Девайну(1974)"
            >
                <Input disabled={true}/>
            </Form.Item>

            <Form.Item
                name="сaloriesMSJ"
                label="Калории по Миффлин—Сан-Жеора(1990)"
            >
                <Input disabled={true}/>
            </Form.Item>

        </Form>}
    </>)
}