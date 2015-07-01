package com.bitdubai.fermat_api.layer.pip_actor.developer;

import com.bitdubai.fermat_api.layer.osa_android.logger_system.LogLevel;
import com.bitdubai.fermat_api.layer.all_definition.enums.Addons;
import com.bitdubai.fermat_api.layer.all_definition.enums.Plugins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ciencias on 6/25/15.
 */
public interface LogTool {

    public List<Plugins> getAvailablePluginList ();

    public List<Addons> getAvailableAddonList ();

    public LogLevel getLogLevel(Plugins plugin);

    public LogLevel getLogLevel(Addons addon);

    public void setLogLevel(Plugins plugin, LogLevel newLogLevel);

    public void setLogLevel(Addons addon, LogLevel newLogLevel);

    public List<ClassHierarchyLevels> getClassesHierarchy(Plugins plugin);

    public void setNewLogLevelInClass(Plugins plugin, HashMap<String, LogLevel> newLogLevelInClass);
}
