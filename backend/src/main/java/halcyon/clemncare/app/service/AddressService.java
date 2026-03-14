package halcyon.clemncare.app.service;

import java.util.Optional;

import halcyon.clemncare.app.dto.AddressDTO;
import halcyon.clemncare.app.model.Address;

public interface AddressService {

    public Address createAddress(AddressDTO addressDTO);

    public Address updateAddress(Long id, AddressDTO addressDTO);

    public Address partialUpdateAddress(Long id, AddressDTO addressDTO);

    public void deleteAddress(Long addressId);

    public Optional<Address> getAddress(Long id);

}
