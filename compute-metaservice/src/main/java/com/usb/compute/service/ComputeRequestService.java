
package com.usb.compute.service;

import com.usb.compute.model.ComputeRequest;
import com.usb.compute.model.RequestUser;
import com.usb.compute.repository.ComputeRequestRepository;
import com.usb.compute.repository.RequestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


@Service
public class ComputeRequestService {

    @Autowired
    private ComputeRequestRepository computeRequestRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RequestUserRepository userRepository;

    public ComputeRequest createComputeRequest(ComputeRequest request) {

        RequestUser user = null;

        /* lookup existing user in the db if specified by id */
        if (request.getUser() != null && request.getUser().getId() != null) {
            Optional<RequestUser> byId = userRepository.findById(request.getUser().getId());
            user = byId.isPresent() ? byId.get() : null;
        }

        /* when user not found, use anything in the db */
        if (user == null) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<RequestUser> cq = cb.createQuery(RequestUser.class);
            Root<RequestUser> root = cq.from(RequestUser.class);
            cq.select(root);

            List<RequestUser> allUsers = entityManager.createQuery(cq).getResultList();
            request.setUser(allUsers.get(0));
        }
        return computeRequestRepository.save(request);
    }

}


