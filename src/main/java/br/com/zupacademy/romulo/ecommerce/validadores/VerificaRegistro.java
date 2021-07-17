package br.com.zupacademy.romulo.ecommerce.validadores;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerificaRegistro implements ConstraintValidator<ExisteRegistro, Long> {

    @Autowired
    private EntityManager em;

    private String atributo;

    private String tabela;

    private boolean nullable;

    @Override
    public void initialize(ExisteRegistro constraintAnnotation) {

        this.atributo = constraintAnnotation.atributo();
        this.tabela = constraintAnnotation.entidade();
        this.nullable = constraintAnnotation.nullable();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {


        if(this.nullable & id == null){
            return true;
        }
        Query query = em.createQuery("SELECT s FROM "+this.tabela+" s where "+this.atributo+"= :id")
                .setParameter("id",id);

        return !query.getResultList().isEmpty();
    }
}
