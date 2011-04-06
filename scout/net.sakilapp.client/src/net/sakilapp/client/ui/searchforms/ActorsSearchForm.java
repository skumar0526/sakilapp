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
package net.sakilapp.client.ui.searchforms;

import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.ResetButton;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.SearchButton;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox.FieldBox;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox.FieldBox.ActorIdBox;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox.FieldBox.ActorIdBox.ActorIdFrom;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox.FieldBox.ActorIdBox.ActorIdTo;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox.FieldBox.FirstNameField;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox.FieldBox.LastNameField;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox.FieldBox.LastUpdateBox;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox.FieldBox.LastUpdateBox.LastUpdateFrom;
import net.sakilapp.client.ui.searchforms.ActorsSearchForm.MainBox.TabBox.FieldBox.LastUpdateBox.LastUpdateTo;
import net.sakilapp.shared.Texts;
import net.sakilapp.shared.formdata.ActorsSearchFormData;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.longfield.AbstractLongField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.shared.ScoutTexts;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@FormData(value = ActorsSearchFormData.class, sdkCommand = SdkCommand.CREATE)
public class ActorsSearchForm extends AbstractSearchForm {

  public ActorsSearchForm() throws ProcessingException {
    super();
  }

  public ActorIdBox getActorIdBox() {
    return getFieldByClass(ActorIdBox.class);
  }

  public ActorIdFrom getActorIdFrom() {
    return getFieldByClass(ActorIdFrom.class);
  }

  public ActorIdTo getActorIdTo() {
    return getFieldByClass(ActorIdTo.class);
  }

  public FieldBox getFieldBox() {
    return getFieldByClass(FieldBox.class);
  }

  public FirstNameField getFirstNameField() {
    return getFieldByClass(FirstNameField.class);
  }

  public LastNameField getLastNameField() {
    return getFieldByClass(LastNameField.class);
  }

  public LastUpdateBox getLastUpdateBox() {
    return getFieldByClass(LastUpdateBox.class);
  }

  public LastUpdateFrom getLastUpdateFrom() {
    return getFieldByClass(LastUpdateFrom.class);
  }

  public LastUpdateTo getLastUpdateTo() {
    return getFieldByClass(LastUpdateTo.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public ResetButton getResetButton() {
    return getFieldByClass(ResetButton.class);
  }

  public SearchButton getSearchButton() {
    return getFieldByClass(SearchButton.class);
  }

  public TabBox getTabBox() {
    return getFieldByClass(TabBox.class);
  }

  @Override
  protected String getConfiguredTitle() {
    return Texts.get("Actors");
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    ActorsSearchFormData formData = new ActorsSearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class TabBox extends AbstractTabBox {

      @Order(10.0)
      public class FieldBox extends AbstractGroupBox {

        @Override
        public String getConfiguredLabel() {
          return Texts.get("searchCriteria");
        }

        @Order(10.0)
        public class ActorIdBox extends AbstractSequenceBox {

          @Override
          protected String getConfiguredLabel() {
            return Texts.get("Id");
          }

          @Order(10.0)
          public class ActorIdFrom extends AbstractLongField {

            @Override
            protected String getConfiguredLabel() {
              return ScoutTexts.get("from");
            }
          }

          @Order(20.0)
          public class ActorIdTo extends AbstractLongField {

            @Override
            protected String getConfiguredLabel() {
              return ScoutTexts.get("to");
            }
          }
        }

        @Order(20.0)
        public class FirstNameField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return Texts.get("FirstName");
          }

        }

        @Order(30.0)
        public class LastNameField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return Texts.get("LastName");
          }

        }

        @Order(40.0)
        public class LastUpdateBox extends AbstractSequenceBox {

          @Override
          protected String getConfiguredLabel() {
            return Texts.get("LastUpdate");
          }

          @Order(10.0)
          public class LastUpdateFrom extends AbstractDateField {

            @Override
            protected String getConfiguredLabel() {
              return ScoutTexts.get("from");
            }
          }

          @Order(20.0)
          public class LastUpdateTo extends AbstractDateField {

            @Override
            protected String getConfiguredLabel() {
              return ScoutTexts.get("to");
            }
          }
        }
      }
    }

    @Order(20.0)
    public class ResetButton extends AbstractResetButton {
    }

    @Order(30.0)
    public class SearchButton extends AbstractSearchButton {
    }
  }

  @Override
  public void startSearch() throws ProcessingException {
    startInternal(new SearchHandler());
  }

  public class SearchHandler extends AbstractFormHandler {

  }
}
