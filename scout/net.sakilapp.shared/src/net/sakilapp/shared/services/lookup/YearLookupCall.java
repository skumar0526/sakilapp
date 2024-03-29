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
package net.sakilapp.shared.services.lookup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.scout.commons.NumberUtility;
import org.eclipse.scout.commons.TypeCastUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class YearLookupCall extends LocalLookupCall {
  private static final int RANGE = 100;
  private static final long serialVersionUID = 1L;
  private Integer m_minYear;
  private Integer m_maxYear;

  @Override
  public LookupRow[] getDataByKey() throws ProcessingException {
    Object key = getKey();
    if (key != null) {
      Integer i = TypeCastUtility.castValue(key, Integer.class);
      if (i != null) {
        return new LookupRow[]{createRow(i.intValue())};
      }
    }
    return new LookupRow[]{};
  }

  @Override
  protected List<LookupRow> execCreateLookupRows() throws ProcessingException {
    String t = getText();
    int v;
    if (t != null) {
      try {
        v = NumberUtility.parseInt(t);
      }
      catch (NumberFormatException e) {
        Calendar c = Calendar.getInstance();
        v = c.get(Calendar.YEAR);
      }
    }
    else {
      Calendar c = Calendar.getInstance();
      v = c.get(Calendar.YEAR);
    }

    int min = NumberUtility.nvl(m_minYear, v - RANGE);
    int max = NumberUtility.nvl(m_maxYear, v + RANGE) + 1;

    List<LookupRow> result = new ArrayList<LookupRow>();
    for (int i = min; i < max; i++) {
      result.add(createRow(i));
    }

    return result;
  }

  /**
   * @param year
   * @return
   */
  private LookupRow createRow(int year) {
    return new LookupRow(Integer.valueOf(year), String.valueOf(year));
  }
}
