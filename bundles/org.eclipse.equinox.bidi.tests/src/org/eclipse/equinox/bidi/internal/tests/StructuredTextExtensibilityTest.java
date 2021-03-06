/*******************************************************************************
 * Copyright (c) 2010, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.equinox.bidi.internal.tests;

import org.eclipse.equinox.bidi.StructuredTextTypeHandlerFactory;
import org.eclipse.equinox.bidi.advanced.IStructuredTextExpert;
import org.eclipse.equinox.bidi.advanced.StructuredTextExpertFactory;
import org.eclipse.equinox.bidi.custom.StructuredTextTypeHandler;

/**
 * Tests contribution of BiDi handlers.
 */
public class StructuredTextExtensibilityTest extends StructuredTextTestBase {

	public void testOtherContributions() {
		StructuredTextTypeHandler handler = StructuredTextTypeHandlerFactory.getHandler("test.ID");
		assertNotNull(handler);

		handler = StructuredTextTypeHandlerFactory.getHandler("badtest");
		assertNull(handler);

		String data, lean, full, model;
		data = "ABC.DEF:HOST-COM=HELLO";
		lean = toUT16(data);
		handler = StructuredTextTypeHandlerFactory.getHandler("test");

		IStructuredTextExpert expert = StructuredTextExpertFactory.getExpert("test.ID");
		full = expert.leanToFullText(lean);

		model = "ABC@.DEF@:HOST@-COM@=HELLO";
		assertEquals("Test 'test' plugin", model, toPseudo(full));
	}

}
