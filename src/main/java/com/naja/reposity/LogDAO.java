package com.naja.reposity;

import com.naja.model.Log;
import com.naja.util.DAOException;

public interface LogDAO {
	public void salvar(Log log) throws DAOException;
}
