package com.c1736.bankservice.configuration;

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
    public static final String ACCOUNT_BANK_NOT_FOUND_MESSAGE = "La cuenta de banco no existe.";
    public static final String USER_NOT_FOUND_MESSAGE = "Este correo no pertenece a ningun usuario.";
    public static final String UNAUTHORIZED_MESSAGE = "No tiene permisos para acceder a este recurso.";
    public static final String NO_DATA_FOUND_MESSAGE = "No data found.";

    public static final String NEW_ACCOUNT = "Estimado/a\n" +
            "\n" +
            "¡Nos complace informarte que tu cuenta bancaria ha sido creada con éxito en Grow Bank!\n" +
            "\n" +
            "Nos sentimos honrados de darte la bienvenida a nuestra familia bancaria y queremos agradecerte por elegir nuestros servicios financieros para tus necesidades bancarias. Estamos comprometidos a brindarte la mejor experiencia bancaria posible y a ayudarte a alcanzar tus metas financieras.\n" +
            "\n" +
            "Con tu nueva cuenta, tendrás acceso a una amplia gama de servicios bancarios, incluyendo banca en línea, aplicaciones móviles, y una variedad de productos financieros diseñados para satisfacer tus necesidades. Una vez más, te damos la bienvenida a Grow Bank y esperamos poder servirte en todas tus necesidades financieras.\n" +
            "\n" +
            "¡Gracias por confiar en nosotros!\n" +
            "\n" +
            "Atentamente,\n" +
            "Grow Bank - Tu Banca Digital\n" +
            "Equipo de Servicio al Cliente";

    public static final String NEW_TRANSFER = "¡Transferencia realizada satisfactoriamente!";
    public static final String NEW_DEPOSIT = "¡Deposito realizado satisfactoriamente";
}
