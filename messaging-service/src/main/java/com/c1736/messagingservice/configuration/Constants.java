package com.c1736.messagingservice.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SWAGGER_TITLE_MESSAGE = "User API";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String MESSAGE_NOT_SEND = "El mensaje no se pudo enviar correctamente.";
    private static final String ALERT_LOGIN = "Hemos detectado múltiples intentos de inicio de sesión fallidos en su cuenta. Si no fue usted quien intentó iniciar sesión, le recomendamos que cambie su contraseña de inmediato y verifique la seguridad de su cuenta. Si necesita ayuda para restablecer su contraseña o tiene alguna pregunta, no dude en ponerse en contacto con nuestro equipo de soporte.\n" +
            "\n" +
            "Atentamente,\n" +
            "\n" +
            "El equipo de Grow Bank - Tu Banca Digital.";

}
