# GymCheck


<h2 align="center">
    Proyecto final de Desarrollo de Aplicaciones Moviles 2
</h2>

<p align="center">
    <b>Desarrollado en lenguaje kotlin, elaborado por Marcelo Rivera, Allan Silva y Roberto Amador.</b>
</p>

<p align="center">
    Una aplicacón de administración de membresias, realizada en kotlin, con posibilidad de ver los productos que ofrece dicho GYM y gestión de su membresia.
</p>

<br />
<p align="center">
    <i>Puedes visitar nuestros perfiles acá:</i>
    <br/><br/>
    <a href="https://github.com/Chocoyito" target="_blank">
        <img src="https://img.shields.io/badge/-Github-000?logo=github&style=for-the-badge&logoColor=white" alt="Bradge Github" />
    </a>
    <a href="https://github.com/MrSty" target="_blank">
        <img src="https://img.shields.io/badge/-Github-000?logo=github&style=for-the-badge&logoColor=white" alt="Bradge Github" />
    </a>
    <a href="https://github.com/Xeppyz" target="_blank">
        <img src="https://img.shields.io/badge/-Github-000?logo=github&style=for-the-badge&logoColor=white" alt="Bradge Github" />
  
</p>
	
## Lenguajes utilizados
	
- Kotlin
- PHP

	
## Funcionalidades de la aplicación
Las funcionalidades de la aplicación incluyen un inicio de sesión que requiere credenciales proporcionadas por el administrador. Una vez que se accede a la aplicación, se mostrará una pantalla de inicio que incluye un sidebar con opciones para ver el perfil del usuario, cerrar sesión y ajustar la configuración. La vista de inicio también mostrará la tienda del gimnasio, incluyendo los suplementos disponibles, los precios y la cantidad en stock. Los usuarios pueden navegar a través de los productos, las suscripciones, las promociones del gimnasio y los precios de las suscripciones utilizando un bottom navigation bar.
	
En la vista del administrador, se podrán registrar nuevos clientes y especificar el tipo de suscripción que está pagando cada cliente, ya sea mensualidad, mes o semana. También podrán dar de baja a los clientes si es necesario y subir productos a la venta, especificando cuántos hay en stock. Además, se podrán poner promociones disponibles en el gimnasio.
Por último, la aplicación se desarrollará en Android Studio 2022, utilizando el lenguaje de programación Kotlin y una base de datos PHP MySQL. Los datos se guardarán en la nube para asegurar su disponibilidad y evitar la pérdida de información.

	
### Directorios dentro del código

<table width="100%">
    <tr>
        <th>Directorio</th>
        <td>Descripción</td>
    </tr>
    <tr>
	<td><strong>Entidades</strong></th>
       <td>Directorio enfocado a las dataclass del proyecto, clases que se utilizan únicamente como un molde para los objetos creados, pues únicamente queremos sus atributos y carecen de metodos propios.</td>
    </tr>
    <tr>
        <td><strong>Adaptadores</strong></th>
        <td>En este directorio se encuentran los adaptadores que nos sirven para mostrar los elementos obtenidos de los recycler view.</td>
    </tr>
    <tr>
        <td><strong>Controladores</strong></th>
        <td>Dentro se encuentran las clases mediadoras encargadas de las operaciones y metodos entre las vistas y las entidades.</td>
    </tr>
</table>

## Cosas a tomar en cuenta

- El código fue construido con la API de Android 26
- Se necesita de la herramienta XAMPP y la base de datos importada en PHPMyAdmin para poder utilizar los datos de la aplicación.
- Al momento de probar la aplicación es necesario colocar su respectiva IPv4 en los diferentes metodos que se encuentran en los controladores. Esto es debido a que la recolección de la IP es manual y no dinámica.
- Se está utizando una libreria externa llamada PHPMailer para el envío de correos electrónicos al momento de crear nuevos usuarios. Aquí el enlace de github: https://github.com/PHPMailer/PHPMailer

🐍 A Snake Eating
	
<p align = "center">
	<img src = "https://github.com/7oSkaaa/7oSkaaa/blob/output/github-contribution-grid-snake.svg?" alt = "Snake Game"/>
</p>
