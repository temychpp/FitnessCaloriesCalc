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

app.listen(8080, () => console.log('REST API is running on http://localhost:8080'));