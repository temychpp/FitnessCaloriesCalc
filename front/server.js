const express = require('express');
const cors = require('cors');
const app = express();

app.use(cors());

/* todo: We will have dozens of rest endpoints. We should generalize end point stubs
e.g. read jsons from stubs folder
 */

app.use('/person/anthro', (req, res) => {
    res.send({
        gender: 'мужской',
        age: '42',
        height: '160',
        weight: '82',
    })
})

app.use('/500/person/anthro', (req, res) => {
    res.status(500).send()
})


app.use('/login', (req, res) => {
    res.send({
        token: 'test123',
        id: 1
    })
})

// ======= CALC ========
app.use('/calc/body_mass_index', (req, res) => {
    res.send("1")
})

app.use('/calc/calories_mifflin_stjeor', (req, res) => {
    res.send("2")
})

app.use('/calc/ideal_weight_lorenz', (req, res) => {
    res.send("3")
})

app.use('/calc/ideal_weight_devine', (req, res) => {
    res.send("4")
})

app.use('/calc/ideal_weight_broca', (req, res) => {
    res.send("5")
})

app.listen(8080, () => console.log('REST API is running on http://localhost:8080'));