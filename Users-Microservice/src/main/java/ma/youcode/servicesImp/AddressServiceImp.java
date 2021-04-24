package ma.youcode.servicesImp;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.youcode.entities.Address;
import ma.youcode.entities.User;
import ma.youcode.repository.AddressRepository;
import ma.youcode.repository.UserRepository;
import ma.youcode.services.AddressService;
import ma.youcode.shared.AddressDto;
import ma.youcode.shared.Utils;

@Service
public class AddressServiceImp implements AddressService {

	@Autowired
	Utils utils;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserRepository userRepository;

//	@Override
//	public List<AddressDto> getAllAddresses(String email) {
//
//		User currentUser = userRepository.findByEmail(email);
//
//		List<Address> addresses = currentUser.getAdmin() == true ? (List<Address>) addressRepository.findAll()
//				: addressRepository.findByUser(currentUser);
//
//		Type listType = new TypeToken<List<AddressDto>>() {
//		}.getType();
//		List<AddressDto> addressesDto = new ModelMapper().map(addresses, listType);
//
//		return addressesDto;
//	}

	@Override
	public Address createAddress(Address address, String email) {

		User currentUser = userRepository.findByEmail(email);
		Address adrr = address;
		adrr.setUser(currentUser);
		adrr.setAddressId(utils.genereteStringId(30));
		Address newAddress = addressRepository.save(adrr);
		return newAddress;
	}

	@Override
	public Address getAddress(String addressId) {
		Address addressEntity = addressRepository.findByAddressId(addressId);
		return addressEntity;
	}

	@Override
	public void deleteAddress(String addressId) {
		Address address = addressRepository.findByAddressId(addressId);

		if (address == null)
			throw new RuntimeException("Address not found");
		addressRepository.delete(address);

	}

	@Override
	public List<AddressDto> getAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

}
