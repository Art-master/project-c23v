package com.core.app.errors

import app.domain.entities.IResponse
import java.lang.RuntimeException

class ProjectException(override val message: String) : RuntimeException(), IResponse