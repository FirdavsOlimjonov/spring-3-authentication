package com.alibou.security.component;

import com.alibou.security.entity.Role;
import com.alibou.security.entity.User;
import com.alibou.security.entity.enums.PageEnum;
import com.alibou.security.entity.enums.PermissionEnum;
import com.alibou.security.entity.enums.RoleEnum;
import com.alibou.security.repository.RoleRepository;
import com.alibou.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String modeType;

    @Override
    public void run(String... args) {
        if (Objects.equals("create", modeType)) {
            addRoles();
            addAdmin();
        }
    }

    private void addAdmin() {
         userRepository.save(
                User.builder()
                        .firstname("Admin")
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("root123"))
                        .role(addSuperAdminRole())
                        .enable(true)
                        .build()
        );
    }

    private Role addSuperAdminRole() {
        return roleRepository.save(
                Role.builder()
                        .name(RoleEnum.SuperOwner)
                        .description("System owner")
                        .pages(Set.of(PageEnum.values()))
                        .defaultPage(PageEnum.HOTELS)
                        .permissions(getAdminPermissions())
                        .build()
        );
    }

    private void addRoles() {
        roleRepository.save(
                Role.builder()
                        .name(RoleEnum.Owner)
                        .description("System owner")
                        .pages(Set.of(PageEnum.ORDERS, PageEnum.BILLING, PageEnum.PROMO_CODE,
                                PageEnum.DASHBOARDS, PageEnum.MYHOTEL, PageEnum.SETTINGS))
                        .defaultPage(PageEnum.BILLING)
                        .permissions(setOfPermissionOfOwner())
                        .build()
        );
        roleRepository.save(
                Role.builder()
                        .name(RoleEnum.Manager)
                        .description("System manager")
                        .pages(Set.of(PageEnum.ORDERS, PageEnum.DASHBOARDS))
                        .defaultPage(PageEnum.ORDERS)
                        .permissions(setOfPermissionOfManager())
                        .build()
        );
    }

    private Set<PermissionEnum> setOfPermissionOfManager() {
        return Arrays.stream(PermissionEnum.values())
                .toList()
                .stream()
                .filter(p -> p.getPage().equals(PageEnum.ORDERS)
                        || p.getPage().equals(PageEnum.DASHBOARDS))
                .collect(Collectors.toSet());
    }

    private Set<PermissionEnum> setOfPermissionOfOwner() {
        return Arrays.stream(PermissionEnum.values())
                .toList()
                .stream()
                .filter(p -> p.getPage().equals(PageEnum.ORDERS)
                        || p.getPage().equals(PageEnum.CATEGORY)
                        || p.getPage().equals(PageEnum.DASHBOARDS)
                        || p.getPage().equals(PageEnum.BILLING)
                        || p.getPage().equals(PageEnum.MYHOTEL))
                .collect(Collectors.toSet());
    }

    private Set<PermissionEnum> getAdminPermissions() {
        return Set.of(PermissionEnum.values());
    }

}
