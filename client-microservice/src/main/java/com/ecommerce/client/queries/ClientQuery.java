package com.ecommerce.client.queries;

import com.ecommerce.client.requestDTO.ClientRequestDTO;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ClientQuery {

    public static Supplier<String> createQueryToFetchClientToSendEmail = () ->
            " SELECT a.id, a.email_address FROM client a WHERE a.email_sent = 'N'";

    public static Function<String, String> createQueryToFetchClientByUsername = (username) -> {

        String query = "";
        query = " SELECT a.id," +
                " a.firstname," +
                " a.lastname," +
                " a.password," +
                " a.status," +
                " a.login_attempt," +
                " a.email_address" +
                " FROM" +
                " client a" +
                " WHERE" +
                " a.id!=0";

        if (!Objects.isNull(username))
            query += " AND a.username= '" + username + "'";

        return query;
    };

    public static Function<ClientRequestDTO, String> createQueryToFetchClientDetails = (requestDTO) -> {

        String query = "";
        query = " SELECT a.id," +
                " a.firstname," +
                " a.lastname," +
                " a.password," +
                " a.status," +
                " a.login_attempt," +
                " a.email_address" +
                " FROM" +
                " client a" +
                " WHERE" +
                " a.id!=0";

        if (!Objects.isNull(requestDTO.getUsername()))
            query += " AND a.username= '" + requestDTO.getUsername() + "'";

        if (!Objects.isNull(requestDTO.getEmailAddress()))
            query += " AND a.email_address= '" + requestDTO.getEmailAddress() + "'";

        return query;
    };

    public static Function<String, String> getClientCountByUsername = (username) ->
            "SELECT COUNT(a.id) FROM client a WHERE a.username = '" + username + "'";

    public static Function<String, String> getClientCountByEmailAddress = (emailAddress) ->
            "SELECT COUNT(a.id) FROM client a WHERE a.email_address = '" + emailAddress + "'";
}