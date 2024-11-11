#include <OneWire.h>
#include <DallasTemperature.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

// Configuración Wi-Fi
#define WIFI_SSID "V2027"
#define WIFI_PASSWORD "Guayaba12.."

// Configuración de pines
#define ONE_WIRE_BUS D2         // Pin para el sensor de temperatura DS18B20
#define TRIGGER_PIN D5          // Pin Trigger para el sensor ultrasónico
#define ECHO_PIN D6             // Pin Echo para el sensor ultrasónico

// Configuración del sensor de temperatura
OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);

WiFiClient client;
HTTPClient http;

// Definición de rangos de temperatura
const float tempMin = 18.0;    // Mínimo de temperatura (18°C)
const float tempMax = 30.0;    // Máximo de temperatura (30°C)

// Configuración del contenedor
const int alturaContenedor = 40;       // Altura total del contenedor en cm
const int limite80 = alturaContenedor * 0.2;  // 80% de la altura del contenedor

void setup() {
  Serial.begin(115200);                   // Inicializamos la comunicación serial
  pinMode(TRIGGER_PIN, OUTPUT);         // Pin Trigger como salida
  pinMode(ECHO_PIN, INPUT);             // Pin Echo como entrada
  digitalWrite(TRIGGER_PIN, LOW);       // Inicializamos el Trigger en LOW

  // Conexión Wi-Fi
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);

  int retries = 0;
  while (WiFi.status() != WL_CONNECTED && retries < 20) {
    delay(5000);
    Serial.print(".");
    retries++;
  }
  if (WiFi.status() != WL_CONNECTED) {
    Serial.println("No se pudo conectar al Wi-Fi");
    return;
  } else {
    Serial.println("Conectado a Wi-Fi");
    Serial.print("IP: ");
    Serial.println(WiFi.localIP());
  }

  sensors.begin();       
  client.setTimeout(10000);   // Inicializamos el sensor de temperatura
  delay(1000);
}

void loop() {
  // Sensor ultrasónico: medida de distancia
  digitalWrite(TRIGGER_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIGGER_PIN, LOW);

  long t = pulseIn(ECHO_PIN, HIGH);     // Tiempo que demora en llegar el eco
  long distancia = t / 59;              // Convertimos el tiempo en distancia

  // Mostrar la distancia en el monitor serial
  Serial.print("Distancia: ");
  Serial.print(distancia);
  Serial.println(" cm");

  // Verificación del nivel del contenedor
  if (distancia <= limite80) {
    Serial.println("¡Alerta! El contenedor está al 80% de su capacidad.");
  }

  delay(1000);  // Pausa de 1 segundo

  // Sensor de temperatura: lectura de temperatura
  sensors.requestTemperatures();        // Solicita la temperatura
  float temp = sensors.getTempCByIndex(0);  // Lee la temperatura en ºC

  Serial.print("Temperatura = ");
  Serial.print(temp);
  Serial.println(" °C");

  // Preparar y enviar datos al servidor SQL
  enviarDatosSQL(temp, distancia);
  delay(10000);  // Pausa de 10 segundos entre lecturas
}

void enviarDatosSQL(float temp, float nivelLlenado) {
  if (WiFi.status() == WL_CONNECTED) {
    // URL del endpoint de tu base de datos
    String serverUrl = "http://192.168.234.84:9090/resultado/guardar";

    // Crear el cuerpo del mensaje en formato x-www-form-urlencoded
    String postData = "resultadoTemperatura=" + String(temp) + "&nivelLlenado=" + String(nivelLlenado);

    // Iniciar solicitud HTTP POST
    http.begin(client, serverUrl);
    http.addHeader("Content-Type", "application/x-www-form-urlencoded");

    int httpResponseCode = http.POST(postData);

    // Verificar el código de respuesta
    if (httpResponseCode == 200) {
      Serial.println("Datos enviados correctamente al servidor SQL");
    } else {
      Serial.print("Error al enviar datos: ");
      Serial.println(httpResponseCode);
      Serial.println(http.errorToString(httpResponseCode).c_str());
    }

    // Finalizar solicitud
    http.end();
  } else {
    Serial.println("WiFi no conectado.");
  }
}
