package ma.youcode.services;

import java.util.List;

import ma.youcode.entities.Address;
import ma.youcode.shared.AddressDto;

public interface AddressService {

	List<AddressDto> getAllAddresses(String email);

	Address createAddress(Address address, String email);

	Address getAddress(String addressId);

	void deleteAddress(String addressId);
}
