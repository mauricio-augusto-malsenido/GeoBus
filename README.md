# GeoBus
Aplicación móvil version beta (todavia faltan algunas funciones y corregir bugs para completarla definitivamente) que está destinada a plataforma Android y que nos muestra en un mapa las ubicaciones en tiempo real de los colectivos de las distintas lineas de la provincia de Tucumán. Estas ubicaciones serán transmitidas a través de los teléfonos móviles de las personas que se encuentren dentro de los colectivos y que tengan esta aplicación instalada en dichos teléfonos, pasando luego estas a estar visibles para las demás personas que consulten la aplicación.

El sistema esta compuesto de la app que reside y se ejecuta en el teléfono móvil y de un servicio REST que se almacena y ejecuta en la nube. Con respecto al almacenamiento de información, se cuenta con una base de datos local que reside en el mismo teléfono y que funciona como una especie de cache que almacenará toda la información de las consultas realizadas por el usuario, y con una base de datos externa que reside en la nube con toda la información necesaria para el funcionamiento de la aplicación, la app accede a la misma únicamente a través del servicio REST ya sea para obtener información, almacenarla o modificarla.

Este sistema se desarrollo utilizando las siguientes tecnologías y herramientas:
- Herramientas de desarrollo: Android Studio (App) y Visual Studio Code (Servicio REST)
- Lenguajes utilizados: Java (App) y NodeJS (Servicio REST)
- Motor de base de datos local: SQLite
- Motor de base de datos externa: MongoDB
- Nube: Google Cloud
- APIs utilizadas: Google Maps, Autenticación de cuentas de Google y Autenticación de cuentas de Facebook
- Libreria utilizada para la conexión con servicio Rest: Retrofit
