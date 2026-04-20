package com.example.MPRRT.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MPRRT.dto.LocationRequestDTO;
import com.example.MPRRT.dto.LocationResponseDTO;
import com.example.MPRRT.entity.Location;
import com.example.MPRRT.mapper.LocationMapper;
import com.example.MPRRT.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public LocationResponseDTO createLocation(LocationRequestDTO locationRequestDTO) {
        Location location = LocationMapper.toEntity(locationRequestDTO);
        Location savedLocation = locationRepository.save(location);
        return LocationMapper.toResponseDTO(savedLocation);
    }

    public List<LocationResponseDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(LocationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<LocationResponseDTO> getLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.map(LocationMapper::toResponseDTO);
    }

    public Location getLocationEntityById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
    }

    public LocationResponseDTO updateLocation(Long id, LocationRequestDTO locationRequestDTO) {
        Optional<Location> existingLocationOpt = locationRepository.findById(id);
        if (existingLocationOpt.isEmpty()) {
            throw new RuntimeException("Location not found with id: " + id);
        }

        Location existingLocation = existingLocationOpt.get();
        existingLocation.setAreaName(locationRequestDTO.getAreaName());
        existingLocation.setCity(locationRequestDTO.getCity());
        existingLocation.setState(locationRequestDTO.getState());
        existingLocation.setLatitude(locationRequestDTO.getLatitude());
        existingLocation.setLongitude(locationRequestDTO.getLongitude());

        Location updatedLocation = locationRepository.save(existingLocation);
        return LocationMapper.toResponseDTO(updatedLocation);
    }

    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new RuntimeException("Location not found with id: " + id);
        }
        locationRepository.deleteById(id);
    }
}
