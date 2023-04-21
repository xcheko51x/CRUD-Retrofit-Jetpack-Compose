const express = require('express')
const mysql = require('mysql')
const bodyParser = require('body-parser')

const app = express()

app.use(function(req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', '*');
    next()
})

app.use(bodyParser.json())

const PUERTO = 3000

const conexion = mysql.createConnection(
    {
        host:'localhost',
        database:'pruebas',
        user:'root',
        password:''
    }
)

app.listen(PUERTO, () => {
    console.log(`Servidor corriendo en el puerto ${PUERTO}`);
})

conexion.connect(error => {
    if(error) throw error
    console.log('Conexión exitosa a la base de datos');
})

let obj = {}

app.get('/', (req, res) => {
    res.send('API')
})

app.get('/usuarios', (req, res) => {
    const query = `SELECT * FROM usuarios;`
    conexion.query(query, (error, resultado) => {
        if(error) return console.error(error.message)

        if(resultado.length > 0) {
            obj.codigo = "200"
            obj.mensaje = "Lista Usuarios"
            obj.data = resultado
            res.json(obj)
        } else {
            obj.codigo = "400"
            obj.mensaje = "No hay registros"
            obj.data = []
            res.json(obj)
        }
    })
})

app.get('/usuarios/:id', (req, res) => {
    const { id } = req.params

    const query = `SELECT * FROM usuarios WHERE id_usuario=${id};`
    conexion.query(query, (error, resultado) => {
        if(error) return console.error(error.message)

        if(resultado.length > 0) {
            obj.codigo = "200"
            obj.mensaje = "Usuario"
            obj.data = resultado
            res.json(obj)
        } else {
            obj.codigo = "400"
            obj.mensaje = "No hay registros"
            obj.data = []
            res.json(obj)
        }
    })
})

app.post('/usuarios/agregar', (req, res) => {
    const usuario = {
        nombre: req.body.nombre,
        email: req.body.email
    }

    const query = `INSERT INTO usuarios SET ?`
    conexion.query(query, usuario, (error) => {
        if(error) return console.error(error.message)

        obj.codigo = "200"
        obj.mensaje = "Se insertó correctamente el usuario"
        obj.data = []
        res.json(obj)
    })
})

app.put('/usuarios/actualizar/:id', (req, res) => {
    const { id } = req.params
    const { nombre, email } = req.body

    const query = `UPDATE usuarios SET nombre='${nombre}', email='${email}' WHERE id_usuario='${id}';`
    conexion.query(query, (error) => {
        if(error) return console.error(error.message)

        obj.codigo = "200"
        obj.mensaje = "Se actualizó correctamente el usuario"
        obj.data = []
        res.json(obj)
    })
})

app.delete('/usuarios/borrar/:id', (req, res) => {
    const { id } = req.params

    const query = `DELETE FROM usuarios WHERE id_usuario=${id};`
    conexion.query(query, (error) => {
        if(error) console.error(error.message)

        obj.codigo = "200"
        obj.mensaje = "Se eliminó correctamente el usuario"
        obj.data = []
        res.json(obj)
    })
})