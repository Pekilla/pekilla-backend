package com.pekilla.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pekilla.global.ForumTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Check;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Customer extends ForumTable implements UserDetails {
    @Size(max = 30)
    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    private String icon;
    private String banner;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany
    @Check(constraints = "follower_id != customer_id")
    @JoinTable(name = "rel_customer_follower", inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private Set<Customer> followers;

    // For the future, to know if as user as entered a code that he received by email.
    @Builder.Default
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isValidated = false;

    @Builder.Default
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDate joinedDate = LocalDate.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.isActive();
    }
}
