package br.com.dacAtividade2.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.dacAtividade2.model.daos.OrientadorDAO;
import br.com.dacAtividade2.model.entities.Orientador;
import br.com.dacAtividade2.model.utils.JPAUtil;

@FacesConverter(forClass = Orientador.class, value="orientadorConverter")
public class OrientadorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Orientador retorno = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				OrientadorDAO orientador = new OrientadorDAO(manager);
				retorno = orientador.obterPorId(Long.parseLong(value));
			}
			return retorno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Orientador orientador = (Orientador) value;
			return orientador.getId().toString();
		}
		return null;
	}
}
