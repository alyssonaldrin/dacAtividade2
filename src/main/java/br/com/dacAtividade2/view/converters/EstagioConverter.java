package br.com.dacAtividade2.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.dacAtividade2.model.daos.EstagioDAO;
import br.com.dacAtividade2.model.entities.Estagio;
import br.com.dacAtividade2.model.utils.JPAUtil;

@FacesConverter(forClass = Estagio.class, value="estagioConverter")
public class EstagioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estagio retorno = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				EstagioDAO estagio = new EstagioDAO(manager);
				retorno = estagio.obterPorId(Long.parseLong(value));
			}
			return retorno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Estagio estagio = (Estagio) value;
			return estagio.getId().toString();
		}
		return null;
	}
}
