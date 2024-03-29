/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package net.sakilapp.ui.swt.application;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * <h3>ApplicationActionBarAdvisor</h3> Used for menu contributions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

  private IWorkbenchAction exitAction;
  private IWorkbenchAction catalogAction;
  private IWorkbenchAction retailAction;
  private IWorkbenchAction customersAction;

  public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
    super(configurer);
  }

  @Override
  protected void makeActions(IWorkbenchWindow window) {
    exitAction = ActionFactory.QUIT.create(window);
    register(exitAction);

    //TODO JBR: change these Actions => should correspond IViewButton of the scout desktop
    catalogAction = ActionFactory.ABOUT.create(window); //net.sakilapp.client.ui.desktop.Desktop.CatalogOutlineViewButton
    catalogAction.setText("Catalog");
    register(catalogAction);

    retailAction = ActionFactory.ABOUT.create(window); //net.sakilapp.client.ui.desktop.Desktop.RetailOutlineViewButton
    retailAction.setText("Retail");
    register(retailAction);

    customersAction = ActionFactory.ABOUT.create(window); //net.sakilapp.client.ui.desktop.Desktop.CustomersOutlineViewButton
    customersAction.setText("Customers");
    register(customersAction);
  }

  @Override
  protected void fillMenuBar(IMenuManager menuBar) {
    MenuManager fileMenu = new MenuManager("&File",
        IWorkbenchActionConstants.M_FILE);
    menuBar.add(fileMenu);
    fileMenu.add(new Separator("additions"));
    fileMenu.add(new Separator("exit"));
    fileMenu.add(exitAction);
  }

  @Override
  protected void fillCoolBar(ICoolBarManager coolBar) {
    IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
    coolBar.add(new ToolBarContributionItem(toolbar, "org.eclipse.ui.main.toolbar"));
    toolbar.add(catalogAction);
    toolbar.add(customersAction);
    toolbar.add(retailAction);
  }

}
