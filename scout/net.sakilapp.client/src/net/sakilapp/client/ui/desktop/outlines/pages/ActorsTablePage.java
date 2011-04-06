/*******************************************************************************
 * Copyright 2011 Jérémie Bresson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.sakilapp.client.ui.desktop.outlines.pages;

import net.sakilapp.client.ui.searchforms.ActorsSearchForm;
import net.sakilapp.shared.Texts;
import net.sakilapp.shared.services.outline.ICatalogOutlineService;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

public class ActorsTablePage extends AbstractPageWithTable<ActorsTablePage.Table> {

  @Override
  protected String getConfiguredIconId() {
    return AbstractIcons.Folder;
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return ActorsSearchForm.class;
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    return SERVICES.getService(ICatalogOutlineService.class).loadActors(filter);
  }

  @Override
  protected String getConfiguredTitle() {
    return Texts.get("ByActor");
  }

  @Override
  protected IPage execCreateChildPage(ITableRow row) throws ProcessingException {
    FilmsTablePage page = new FilmsTablePage();
    page.setActorId(getTable().getActorIdColumn().getValue(row));
    return page;
  }

  @Order(10.0)
  public class Table extends AbstractTable {

    public FirstNameColumn getFirstNameColumn() {
      return getColumnSet().getColumnByClass(FirstNameColumn.class);
    }

    public FirstNameColumn getLastNameColumn() {
      return getColumnSet().getColumnByClass(FirstNameColumn.class);
    }

    public LastUpdateColumn getLastUpdateColumn() {
      return getColumnSet().getColumnByClass(LastUpdateColumn.class);
    }

    @Override
    protected String getConfiguredDefaultIconId() {
      return AbstractIcons.Folder;
    }

    public ActorIdColumn getActorIdColumn() {
      return getColumnSet().getColumnByClass(ActorIdColumn.class);
    }

    @Order(10.0)
    public class ActorIdColumn extends AbstractLongColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return Texts.get("Id");
      }

      @Override
      protected boolean getConfiguredPrimaryKey() {
        return true;
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 30;
      }
    }

    @Order(20.0)
    public class FirstNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return Texts.get("FirstName");
      }

      @Override
      protected boolean getConfiguredSummary() {
        return true;
      }

      @Override
      protected int getConfiguredWidth() {
        return 160;
      }
    }

    @Order(25.0)
    public class LastNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return Texts.get("LastName");
      }

      @Override
      protected boolean getConfiguredSummary() {
        return true;
      }

      @Override
      protected int getConfiguredWidth() {
        return 160;
      }
    }

    @Order(30.0)
    public class LastUpdateColumn extends AbstractDateColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return Texts.get("LastUpdate");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 160;
      }
    }
  }
}
