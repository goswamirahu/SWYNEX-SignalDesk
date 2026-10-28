
package com.signaldesk.controller;

import com.signaldesk.entity.Incident;
import com.signaldesk.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Incident createIncident(@Valid @RequestBody Incident incident) {
        return incidentService.createIncident(incident);
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Long id) {
        return incidentService.getIncidentById(id);
    }

    @PutMapping("/{id}")
    public Incident updateIncident(
            @PathVariable Long id,
            @Valid @RequestBody Incident incident) {
        return incidentService.updateIncident(id, incident);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
    }

    @GetMapping("/search")
    public List<Incident> searchIncidents(
            @RequestParam String keyword) {
        return incidentService.searchIncidents(keyword);
    }

    @GetMapping("/filter/status")
    public List<Incident> filterByStatus(
            @RequestParam String status) {
        return incidentService.filterByStatus(status);
    }

    @GetMapping("/filter/priority")
    public List<Incident> filterByPriority(
            @RequestParam String priority) {
        return incidentService.filterByPriority(priority);
    }

    @GetMapping("/filter/category")
    public List<Incident> filterByCategory(
            @RequestParam String category) {
        return incidentService.filterByCategory(category);
    }

    @GetMapping("/page")
    public Page<Incident> getIncidentsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        return incidentService.getIncidentsPage(
                page, size, sortBy, direction);
    }

    @GetMapping("/dashboard")
    public Map<String, Long> getDashboardStatistics() {
        return incidentService.getDashboardStatistics();
    }
}
