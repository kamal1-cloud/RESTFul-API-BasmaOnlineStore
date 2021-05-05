package ma.youcode.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.models.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
