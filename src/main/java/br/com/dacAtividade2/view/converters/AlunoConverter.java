package br.com.dacAtividade2.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.dacAtividade2.model.daos.AlunoDAO;
import br.com.dacAtividade2.model.entities.Aluno;
import br.com.dacAtividade2.model.utils.JPAUtil;

@FacesConverter(forClass = Aluno.class, value="alunoConverter")
public class AlunoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Aluno retorno = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				AlunoDAO aluno = new AlunoDAO(manager);
				retorno = aluno.obterPorId(Long.parseLong(value));
			}
			return retorno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Aluno aluno = (Aluno) value;
			return aluno.getId().toString();
		}
		return null;
	}
}
