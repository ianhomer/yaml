/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright 2010 bemoko 
 */
import com.bemoko.live.platform.mwc.plugins.AbstractPlugin
import org.ho.yaml.Yaml

class YamlReader extends AbstractPlugin {
  def data, filepath, encoding
  def DEFAULT_ENCODING="UTF-8"

  /**
   * Load the yaml data
   */
  void initialise(Map p) {
    filepath = p.get("filepath")
    encoding = p.get("encoding") ?: DEFAULT_ENCODING
    String resource = this.findResource(filepath)
    if (resource == null) throw new Exception("Can't find " + filepath)
    def inputStream
    try {
      inputStream = new FileInputStream(new File(resource))
      data = Yaml.load(new BufferedReader(new InputStreamReader(inputStream,encoding)))
    } finally {
      org.apache.commons.io.IOUtils.closeQuietly(inputStream)
    }
  }
    
  /**
   * Get accessor - direct to YAML data object
   */
  def get(key) { 
    data[key] 
  }  
}