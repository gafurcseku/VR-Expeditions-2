package com.robotlab.expeditions2.activity.expedition;

import com.robotlab.expeditions2.model.Expedition;

/**
 * This interface use know which expedition item use click to saw detail of expedition
 */

public interface ItemClick {
    void OnClick(Expedition expedition, Boolean isMyExpedition);
}
