package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Employee;
import com.mycompany.myapp.repository.EmployeeRepository;
import com.mycompany.myapp.service.EmployeeService;
import com.mycompany.myapp.service.dto.EmployeeDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;


@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private static final String ENTITY_NAME = "restEmployee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmployeeService employeeService;

    private final EmployeeRepository employeeRepository;

    public EmployeeResource(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }


    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
        log.debug("REST request to save Employee : {}", employeeDTO);
        if (employeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new employee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId());
        if (employeeOptional.isEmpty()){
            throw new BadRequestAlertException("Employee Id exists already", ENTITY_NAME, "employeeIdexists");
        }
        EmployeeDTO result = employeeService.save(employeeDTO);
        return ResponseEntity
            .created(new URI("/api/employees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PatchMapping(value = "/employees/{employeeId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EmployeeDTO> partialUpdateEmployee(
        @PathVariable(value = "employeeId", required = false) final String id,
        @PathVariable(value = "salary", required = false) final Long salary
    ) throws URISyntaxException {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setSalary(salary);
        log.debug("REST request to partial update Employee partially : {}, {}", id, salary);
        if (!employeeRepository.findByEmployeeId(id).isPresent()) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Optional<EmployeeDTO> result = employeeService.partialUpdate(employeeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, employeeDTO.getId().toString())
        );
    }


    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Employees");
        Page<EmployeeDTO> page = employeeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable String employeeId) {
        log.debug("REST request to get Employee : {}", employeeId);
        Optional<EmployeeDTO> employeeDTO = employeeService.findByEmployeeId(employeeId);
        return ResponseUtil.wrapOrNotFound(employeeDTO);
    }
}
