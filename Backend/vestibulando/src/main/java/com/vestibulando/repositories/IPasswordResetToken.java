package com.vestibulando.repositories;

import com.vestibulando.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPasswordResetToken extends JpaRepository<PasswordResetToken,Long> {


}
