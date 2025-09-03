package co.com.pragma.consumer.api.model.request;

public record UserExistsRequest(
	String email,
	String identificationNumber
) {}
