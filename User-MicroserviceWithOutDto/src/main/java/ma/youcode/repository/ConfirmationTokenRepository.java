package ma.youcode.repository;

import org.springframework.data.repository.CrudRepository;

import ma.youcode.entities.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
