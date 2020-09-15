package com.mensagens.mensagensapi.repository.cidade;

import com.mensagens.mensagensapi.model.Cidade;
import com.mensagens.mensagensapi.repository.filter.CidadeFilter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/* Utilizando Criteria como metodo de busca */
public class CidadeRepositoryImpl implements CidadeRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> filtrar(CidadeFilter cidadeFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
        Root<Cidade> root = criteria.from(Cidade.class);

        //criar as restrições
        Predicate[] predicates = criarRestricoes(cidadeFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Cidade> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    private Predicate[] criarRestricoes(CidadeFilter cidadeFilter, CriteriaBuilder builder, Root<Cidade> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(cidadeFilter.getNome())) {
            predicates.add(
                    builder.like(builder.lower(root.get("nome")), "%" + cidadeFilter.getNome().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(cidadeFilter.getEstado())) {
            predicates.add(
                    builder.like(builder.lower(root.get("estado")), "%" + cidadeFilter.getEstado().toLowerCase() + "%"));
        }

        //transformando-os os predicates em um array de predicates para retornar.
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
