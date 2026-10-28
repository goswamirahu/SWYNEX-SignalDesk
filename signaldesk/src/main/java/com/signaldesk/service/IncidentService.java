
package com.signaldesk.service;

import com.signaldesk.entity.Incident;
import com.signaldesk.repository.IncidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public Incident createIncident(Incident incident) {
        incident.setId(null);
        return incidentRepository.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Incident getIncidentById(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() ->
                        new org.springframework.web.server.ResponseStatusException(
                                org.springframework.http.HttpStatus.NOT_FOUND,
                                "Incident not found with ID: " + id));
    }

    public Incident updateIncident(Long id, Incident updatedIncident) {
        Incident existing = getIncidentById(id);

        existing.setTitle(updatedIncident.getTitle());
        existing.setDescription(updatedIncident.getDescription());
        existing.setCategory(updatedIncident.getCategory());
        existing.setPriority(updatedIncident.getPriority());
        existing.setStatus(updatedIncident.getStatus());

        return incidentRepository.save(existing);
    }

    public void deleteIncident(Long id) {
        Incident existing = getIncidentById(id);
        incidentRepository.delete(existing);
    }

    public List<Incident> searchIncidents(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllIncidents();
        }

        String searchTerm = keyword.trim();

        return incidentRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        searchTerm, searchTerm);
    }

    public List<Incident> filterByStatus(String status) {
        return incidentRepository.findByStatusIgnoreCase(status);
    }

    public List<Incident> filterByPriority(String priority) {
        return incidentRepository.findByPriorityIgnoreCase(priority);
    }

    public List<Incident> filterByCategory(String category) {
        return incidentRepository.findByCategoryIgnoreCase(category);
    }

    public Page<Incident> getIncidentsPage(
            int page, int size, String sortBy, String direction) {

        if (page < 0 || size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page must be >= 0 and size must be between 1 and 100");
        }

        List<String> allowedFields =
                List.of("id", "title", "category", "priority", "status");

        if (!allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        Sort.Direction sortDirection =
                "desc".equalsIgnoreCase(direction)
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                page, size, Sort.by(sortDirection, sortBy));

        return incidentRepository.findAll(pageable);
    }

    public Map<String, Long> getDashboardStatistics() {
        Map<String, Long> statistics = new LinkedHashMap<>();

        statistics.put("totalIncidents", incidentRepository.count());
        statistics.put("openIncidents",
                incidentRepository.countByStatusIgnoreCase("OPEN"));
        statistics.put("resolvedIncidents",
                incidentRepository.countByStatusIgnoreCase("RESOLVED"));
        statistics.put("highPriorityIncidents",
                incidentRepository.countByPriorityIgnoreCase("HIGH"));

        return statistics;
    }
}
