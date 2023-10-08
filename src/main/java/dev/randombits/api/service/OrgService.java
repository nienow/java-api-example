package dev.randombits.api.service;

import dev.randombits.api.model.Org;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class OrgService {

    private final List<Org> allOrgs = new ArrayList<>();

    public Org createOrg(String name) {
        Org org = new Org();
        org.setId(UUID.randomUUID().toString());
        org.setName(name);
        this.allOrgs.add(org);
        return org;
    }

    public List<Org> getOrgs() {
        return this.allOrgs;
    }

    public Org getOrg(String id) {
        return this.allOrgs.stream().filter(org -> org.getId().equals(id)).findFirst().get();
    }
}
