package halcyon.clemncare.app.service.implementation;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.AddressDTO;
import halcyon.clemncare.app.exception.AddressNotFoundException;
import halcyon.clemncare.app.model.Address;
import halcyon.clemncare.app.repositories.HomeAddressRepository;
import halcyon.clemncare.app.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private HomeAddressRepository homeAddressRepository;

    @Override
    public Address createAddress(AddressDTO addressDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        return homeAddressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, AddressDTO addressDTO) {
        Optional<Address> optionalAddress = homeAddressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            BeanUtils.copyProperties(addressDTO, existingAddress);
            return homeAddressRepository.save(existingAddress);
        } else {
            throw new AddressNotFoundException("Address with ID " + id + " not found");
        }
    }

    @Override
    public Address partialUpdateAddress(Long id, AddressDTO addressDTO) {
        Optional<Address> optionalAddress = homeAddressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            BeanUtils.copyProperties(addressDTO, existingAddress, getNullPropertyNames(addressDTO));
            return homeAddressRepository.save(existingAddress);
        } else {
            throw new AddressNotFoundException("Address with ID " + id + " not found");
        }
    }

    @Override
    public void deleteAddress(Long addressId) {
        homeAddressRepository.deleteById(addressId);
    }

    @Override
    public Optional<Address> getAddress(Long addressId) {
        return homeAddressRepository.findById(addressId);
    }

    private String[] getNullPropertyNames(AddressDTO homeAddressDTO) {
        final BeanWrapper src = new BeanWrapperImpl(homeAddressDTO);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
