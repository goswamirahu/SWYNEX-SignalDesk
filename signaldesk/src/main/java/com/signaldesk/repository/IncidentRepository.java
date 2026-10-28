
package com.signaldesk.repository;

import com.signaldesk.entity.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    // Search incidents by title or description
    List<Incident> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title, String description);

    // Filter incidents by status
    List<Incident> findByStatusIgnoreCase(String status);

    // Filter incidents by priority
    List<Incident> findByPriorityIgnoreCase(String priority);

    // Filter incidents by category
    List<Incident> findByCategoryIgnoreCase(String category);

    // Paginated incident listing
    Page<Incident> findAll(Pageable pageable);

    // Dashboard statistics
    long countByStatusIgnoreCase(String status);

    long countByPriorityIgnoreCase(String priority);
}
